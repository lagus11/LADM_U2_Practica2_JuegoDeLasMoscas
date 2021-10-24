package mx.tecnm.tepic.ladm_u2_practica2_juegodelasmoscas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Mosca(l:Lienzo,ima:Int) {
    var x = 0f
    var y = 0f
    var subirx = 0
    var subiry = 0
    var cambio = 0
    var imagen = BitmapFactory.decodeResource(l.resources,ima)
    var Aplastada = 0
    var intento = true

    init{
        x = (Math.random()*2100).toFloat()
        y = (Math.random()*1000).toFloat()
        subirx = (Math.random()*2).toInt()
        subiry = (Math.random()*2).toInt()
    }

    fun moverMoscas(){
        if(Aplastada==0){
                if (subirx == 1) {
                    x += (Math.random()*3).toInt()+(Math.random()*3).toInt()
                } else {
                    x -= (Math.random()*3).toInt()+(Math.random()*3).toInt()
                }
                if (subiry == 1) {
                    y += (Math.random()*3).toInt()+(Math.random()*3).toInt()
                } else {
                    y -= (Math.random()*3).toInt()+(Math.random()*3).toInt()
                }
            if(y>800){subiry=2}
            if(y<0){subiry=1}
            if(x>2100){subirx=2}
            if(x<0){subirx=1}
            cambio++
            if(cambio==500){
                subirx = (Math.random()*2).toInt()
                subiry = (Math.random()*2).toInt()
                cambio=0
            }
        }
    }//moverMosca

    fun estaenArea(toqueX:Float,toqueY:Float):Boolean{

        var x2 = x+imagen.width
        var y2 = y+imagen.height
        /*
            x,y   x2,y
            ______
            |    |
            ------
            x,y2   x2,y2

         */
        if(toqueX >=x && toqueX <=x2 && intento){
            if(toqueY >=y && toqueY <=y2 && intento){
                intento = false
                return true
            }

        }
        return false
    }

    fun moscaAplastada(){
        Aplastada=1
    }
}