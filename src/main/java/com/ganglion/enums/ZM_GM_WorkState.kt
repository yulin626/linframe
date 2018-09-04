package com.ganglion.enums

enum class ZM_GM_WorkState {
    /// <summary>
    /// 待分发
    /// </summary>
    ZM_GM_WorkState_BeDistributed,
    /// <summary>
    /// 待接单
    /// </summary>
    ZM_GM_WorkState_Pending,
    /// <summary>
    /// 处理中
    /// </summary>
    ZM_GM_WorkState_Process,
    /// <summary>
    /// 已解决
    /// </summary>
    ZM_GM_WorkState_Resolved,
    /// <summary>
    /// 已废弃
    /// </summary>
    ZM_GM_WorkState_Obsolete
}