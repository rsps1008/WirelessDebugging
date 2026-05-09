package com.rsps1008.wirelessdebugging

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.service.quicksettings.TileService
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            openWirelessDebuggingPage()
        }
    }

    private fun openWirelessDebuggingPage() {
        val wirelessDebuggingComponent = ComponentName(
            "com.android.settings",
            "com.android.settings.development.qstile.DevelopmentTiles\$WirelessDebugging"
        )

        val directIntent = Intent(TileService.ACTION_QS_TILE_PREFERENCES).apply {
            putExtra(Intent.EXTRA_COMPONENT_NAME, wirelessDebuggingComponent)
        }

        val developerOptionsIntent = Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)

        try {
            when {
                directIntent.resolveActivity(packageManager) != null -> {
                    startActivity(directIntent)
                }
                developerOptionsIntent.resolveActivity(packageManager) != null -> {
                    startActivity(developerOptionsIntent)
                }
                else -> {
                    Toast.makeText(
                        this,
                        "找不到可開啟的開發者選項頁面",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "找不到可開啟的開發者選項頁面",
                Toast.LENGTH_LONG
            ).show()
        } finally {
            finish()
        }
    }
}
