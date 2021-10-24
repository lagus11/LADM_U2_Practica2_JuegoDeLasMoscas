package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity3 : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var extra = intent.extras!!
        var moscas = extra.getInt("CantidadMoscas")
        var velocidad = extra.getInt("VelocidadJuego")
        setContentView(Lienzo(this,moscas,velocidad))

    }
}