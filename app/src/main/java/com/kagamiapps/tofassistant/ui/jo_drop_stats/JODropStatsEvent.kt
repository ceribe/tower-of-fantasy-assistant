package com.kagamiapps.tofassistant.ui.jo_drop_stats

import com.kagamiapps.tofassistant.data.consts.Region

sealed class JODropStatsEvent {
    data class OnRegionChange(val region: Region): JODropStatsEvent()
}
