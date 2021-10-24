package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var extra = intent.extras!!
        var moscas = extra.getString("estado")

        status.text = "${moscas}"

        val ventana3 = Intent(this, MainActivity3::class.java)
        aceptar.setOnClickListener {
            var operacion = opcionJuego.selectedItemPosition

            when (operacion) {
                0 -> {

                    ventana3.putExtra("CantidadMoscas", 80)
                    ventana3.putExtra("VelocidadJuego", 8)
                    startActivity(ventana3)
                }
                1 -> {
                    ventana3.putExtra("CantidadMoscas", 90)
                    ventana3.putExtra("VelocidadJuego", 5)
                    startActivity(ventana3)

                }
                2 -> {
                    ventana3.putExtra("CantidadMoscas", 100)
                    ventana3.putExtra("VelocidadJuego", 2)
                    startActivity(ventana3)
                }
            }//when
        }//setOn
    }
}

    /*
    /*
    <TextView
        android:layout_marginTop="30dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Cantindad de Moscas:"
        android:textSize="20dp"
        android:textColor="@color/black"
        android:gravity="center"/>

    <TextView
        android:id="@+id/cantidadMoscas"
        android:layout_marginTop="10dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="80 Moscas"
        android:textSize="15dp"
        android:textColor="@color/black"
        android:gravity="center"/>

    <TextView
        android:layout_marginTop="30dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Velocidad Juego:"
        android:textSize="20dp"
        android:textColor="@color/black"
        android:gravity="center"/>

    <TextView
        android:id="@+id/velocidad"
        android:layout_marginTop="10dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Lento"
        android:textSize="15dp"
        android:textColor="@color/black"
        android:gravity="center"/>
    **/