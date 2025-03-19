package com.pickpoint.pickpoint.ui.model.setting

import androidx.annotation.StringRes
import com.pickpoint.pickpoint.R

enum class PointThemeSetting(
    @StringRes val res: Int, val value: String, val index: Int
) {
    PROTOTYPE(R.string.prototype, "prototype", 0),
    COMING_SOON(R.string.coming_soon, "coming soon...", 1);

}

