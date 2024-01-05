package com.azalea.cafe

import androidx.`annotation`.ColorRes
import androidx.`annotation`.DrawableRes
import androidx.`annotation`.StringRes
import androidx.`annotation`.StyleRes
import kotlin.Int

public object Cafe {
  public object drawable {
    @DrawableRes
    public val ic_launcher_background: Int = com.azalea.cafe.R.drawable.ic_launcher_background

    @DrawableRes
    public val ic_launcher_foreground: Int = com.azalea.cafe.R.drawable.ic_launcher_foreground
  }

  public object mipmap {
    @DrawableRes
    public val ic_launcher: Int = com.azalea.cafe.R.mipmap.ic_launcher

    @DrawableRes
    public val ic_launcher_round: Int = com.azalea.cafe.R.mipmap.ic_launcher_round
  }

  public object color {
    @ColorRes
    public val purple_200: Int = com.azalea.cafe.R.color.purple_200

    @ColorRes
    public val purple_500: Int = com.azalea.cafe.R.color.purple_500

    @ColorRes
    public val purple_700: Int = com.azalea.cafe.R.color.purple_700

    @ColorRes
    public val teal_200: Int = com.azalea.cafe.R.color.teal_200

    @ColorRes
    public val teal_700: Int = com.azalea.cafe.R.color.teal_700

    @ColorRes
    public val black: Int = com.azalea.cafe.R.color.black

    @ColorRes
    public val white: Int = com.azalea.cafe.R.color.white
  }

  public object string {
    @StringRes
    public val app_name: Int = com.azalea.cafe.R.string.app_name
  }

  public object style {
    @StyleRes
    public val Theme_Cafe: Int = com.azalea.cafe.R.style.Theme_Cafe
  }
}
