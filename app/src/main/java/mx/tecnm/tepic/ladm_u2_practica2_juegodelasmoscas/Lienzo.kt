package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat.startActivity

class Lienzo(act: MainActivity3,cantidadMoscas :Int,velocidadJuego:Int): View(act) {

    val principal = act
    val vel = velocidadJuego
    val cantMoscas = cantidadMoscas
    val imgMosca = BitmapFactory.decodeResource(principal.resources, R.drawable.mosca)
    val imgMoscaAplastada = BitmapFactory.decodeResource(principal.resources, R.drawable.aplastada1)
    val mosca = Hilo(this,vel, cantMoscas)
    val tiempo = Hilo2()
    var aplastadas = 0
    var bandera = 0

    init {
        mosca.start()//hilo para mover moscas
        tiempo.start()//hilo para contar el tiempo
    }//init

    override fun onDraw(c: Canvas){
        super.onDraw(c)
        val p = Paint()
        p.textSize = 70f
        p.color = Color.RED
        c.drawText((61-tiempo.contador).toString(),15f,50f,p)//contador tiempo
        p.color = Color.BLACK
        p.textSize = 60f
        c.drawText("Moscas T: "+(cantMoscas-aplastadas),1550f,50f,p)//cantidad moscas

        (0..(cantMoscas-1)).forEach{
                //fin juego tiempo      //fin de juego gano juego   //condicion entre una vez al if
            if((tiempo.contador==60 || aplastadas==cantMoscas) && bandera==0) {
                mosca.detenerJuego()//cambiar la condicion while de hilo
                bandera =1//bandera para que entre al if una vez "preguntar"
                val ventana2 = Intent(principal, MainActivity2::class.java)
                if(aplastadas==cantMoscas){//condicion aplastada==cantMoscas para indicar que gano en el juego
                    ventana2.putExtra("estado", "Ganaste")//mensaje de juego
                }else{
                    ventana2.putExtra("estado", "Perdiste")//mensaje de juego
                }
                principal.startActivity(ventana2)//voy a la ventana 2
            }else{
                if (mosca.mosca[it].Aplastada == 1) {//pregunto si el estatus mosca esta en aplastada
                    c.drawBitmap(imgMoscaAplastada, mosca.mosca[it].x, mosca.mosca[it].y, Paint())//dibujo la mosca aplastada
                } else {
                    c.drawBitmap(imgMosca, mosca.mosca[it].x, mosca.mosca[it].y, Paint())//dibujo la mosca normal
                }
            }
        }//forEach
    }//override

    override fun onTouchEvent(e: MotionEvent): Boolean {
        super.onTouchEvent(e)
        val accion = e.action

        when(accion){
            MotionEvent.ACTION_DOWN->{
                (0..(cantMoscas-1)).forEach{
                    if(mosca.mosca[it].estaenArea(e.x,e.y)){//si el click esta dentro del area de la imagen
                        mosca.mosca[it].moscaAplastada()//cambio el estado de la mosca a aplastada
                        aplastadas++ //incremento el contador de aplastada para evaluar final del juego si estan todas
                    }//if
                }//forEach
            }//MotionEvent
        }//when
        invalidate()
        return true
    }//onTouchEvent
}//class

//hilo para crear la cantindad de moscas y moverlas
class Hilo(p:Lienzo,velocidad:Int, cantidadMoscas:Int):Thread(){
    val puntero = p
    val mosca = ArrayList<Mosca>()
    val vel = velocidad
    val canMoscas = cantidadMoscas
    var detener = true

    fun detenerJuego(){
        detener = false
    }

    init{
        (1..canMoscas).forEach {
            val currumino = Mosca(p,R.drawable.mosca)
            mosca.add(currumino)
        }//foreach
    }//init

    override fun run(){
        super.run()
        while(detener) {
            (0..(canMoscas-1)).forEach{
                mosca[it].moverMoscas()
            }
            puntero.principal.runOnUiThread {
                puntero.invalidate()
            }//runOnUiThread
            sleep(vel.toLong())
        }//while
    }//run
}//Hilo

/*Hilo funciona para contar los 60 segundos*/
class Hilo2():Thread(){
    var contador = 0
    override fun run(){
        super.run()
        while(contador<61) {
            contador++
            sleep(1000)
        }//while
    }//run
}