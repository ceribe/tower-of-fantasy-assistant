package com.kagamiapps.tofassistant.ui.add_edit_jo_loot

import androidx.lifecycle.SavedStateHandle
import com.kagamiapps.tofassistant.data.JOLoot
import com.kagamiapps.tofassistant.data.JOLootRepository
import com.kagamiapps.tofassistant.data.consts.Drop
import com.kagamiapps.tofassistant.data.consts.JODifficulty
import com.kagamiapps.tofassistant.data.consts.JointOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

internal class AddEditJOLootViewModelTest {

    class JOLootRepositoryMock : JOLootRepository {

        private val loots = emptyList<JOLoot>().toMutableList()

        override suspend fun upsertLoot(loot: JOLoot) {
            loots.add(loot)
        }

        override suspend fun deleteLoot(loot: JOLoot) {
            loots.remove(loot)
        }

        override suspend fun deleteLootById(id: Int) {
            loots.removeIf { it.id == id }
        }

        override suspend fun getLootById(id: Int): JOLoot? {
            return loots.find { it.id == id }
        }

        override fun getLoots(): Flow<List<JOLoot>> {
            TODO()
        }

        override suspend fun getLastLoot(): JOLoot? {
            return loots.lastOrNull()
        }
    }

    private lateinit var viewModel: AddEditJOLootViewModel
    private val repository = JOLootRepositoryMock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined) // I wanted to use a better way, but it's deprecated
        val stateHande = SavedStateHandle()
        stateHande["id"] = -1
        viewModel = AddEditJOLootViewModel(repository, stateHande)
    }

    @Test
    fun should_chest_no_change() {
        viewModel.onEvent(
            AddEditJOLootEvent.OnChestNoChange(
                2
            )
        )
        assertEquals(2, viewModel.chestNo)
    }

    @Test
    fun should_difficulty_change() {
        viewModel.onEvent(
            AddEditJOLootEvent.OnDifficultyChange(
                JODifficulty.L60
            )
        )
        assertEquals(JODifficulty.L60, viewModel.difficulty)
    }

    @Test
    fun should_drops_change() {
        val newDrops = listOf(Drop.CrowMatrix, Drop.KingMatrix, Drop.SSRArmor)
        viewModel.onEvent(
            AddEditJOLootEvent.OnDropsChange(
                newDrops
            )
        )
        assertEquals(3, viewModel.drops.size)
        for (i in newDrops.indices) {
            assertEquals(newDrops[i], viewModel.drops[i])
        }
    }

    @Test
    fun should_is_chip_used_change() {
        viewModel.onEvent(
            AddEditJOLootEvent.OnIsChipUsedChange(
                false
            )
        )
        assertEquals(false, viewModel.isChipUsed)

        viewModel.onEvent(
            AddEditJOLootEvent.OnIsChipUsedChange(
                true
            )
        )
        assertEquals(true, viewModel.isChipUsed)
    }

    @Test
    fun should_jo_change() {
        viewModel.onEvent(
            AddEditJOLootEvent.OnJOChange(
                JointOperation.QuarantineArea
            )
        )
        assertEquals(JointOperation.QuarantineArea, viewModel.jo.value)
    }

    @Test
    fun should_add_new_loot() {
        runBlocking {
            val lastLoot = repository.getLastLoot()
            viewModel.onEvent(AddEditJOLootEvent.OnSaveJOLootClick)
            assertNotEquals(lastLoot, repository.getLastLoot())
        }
    }

    @Test
    fun should_add_new_drop() {
        val count = viewModel.drops.size
        viewModel.onEvent(
            AddEditJOLootEvent.OnAddNewDrop(
                Drop.CrowMatrix
            )
        )
        assertEquals(count + 1, viewModel.drops.size)
        assertEquals(Drop.CrowMatrix, viewModel.drops.last())
    }

    @Test
    fun should_remove_drop() {
        viewModel.onEvent(
            AddEditJOLootEvent.OnDropsChange(
                listOf(Drop.CrowMatrix, Drop.KingMatrix)
            )
        )

        viewModel.onEvent(
            AddEditJOLootEvent.OnDeleteDrop(
                1
            )
        )

        assertEquals(1, viewModel.drops.size)
        assertEquals(Drop.CrowMatrix, viewModel.drops[0])
    }

    @Test
    fun should_is_new_be_set() {
        val stateHande = SavedStateHandle()
        stateHande["id"] = -1
        val viewModel1 = AddEditJOLootViewModel(repository, stateHande)
        assertEquals(true, viewModel1.isNew)

        stateHande["id"] = 1
        val viewModel2 = AddEditJOLootViewModel(repository, stateHande)
        assertEquals(false, viewModel2.isNew)
    }
}