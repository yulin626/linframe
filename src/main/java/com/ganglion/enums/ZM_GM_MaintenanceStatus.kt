package com.ganglion.enums;

enum class ZM_GM_MaintenanceStatus {
    /// <summary>
    /// 正常
    /// </summary>
    ZM_GM_MaintenanceStatus_Normal,

    /// <summary>
    /// 巡检中
    /// </summary>
    ZM_GM_MaintenanceStatus_Inspection,

    /// <summary>
    /// 巡检异常
    /// </summary>
    ZM_GM_MaintenanceStatus_InspectionException,

    /// <summary>
    /// 检修中
    /// </summary>
    ZM_GM_MaintenanceStatus_Overhaul,

    /// <summary>
    /// 检修异常
    /// </summary>
    ZM_GM_MaintenanceStatus_OverhaulException
}