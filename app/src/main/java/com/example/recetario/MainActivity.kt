package com.example.recetario

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recetario.ADAPTER.TipoComidaAdapter
import com.example.recetario.DATA.Receta
import com.example.recetario.DATA.TipoComida
import com.example.recetario.DB.AppDatabase
import com.example.recetario.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //val app = applicationContext as RecetarioApp

    var listaTiposComida = emptyList<TipoComida>()
    var tipos: List<TipoComida> = listOf(
        TipoComida(0,"Comida americana", "https://media.istockphoto.com/photos/american-food-picture-id1005290910"),
        TipoComida(0,"Comida mexicana", "https://i0.wp.com/lallorona.es/wp-content/uploads/2020/12/curiosidades-de-la-comida-mexicana.jpg?resize=800%2C473&ssl=1"),
        TipoComida(0,"Comida japonesa", "https://mochilerosentailandia.com/wp-content/uploads/2018/07/1-11.jpg"),
        TipoComida(0,"Comida italiana", "https://ginos.com.mx/wp-content/uploads/2021/01/dsc_3339.jpg"),
        TipoComida(0, "Postres", "https://img.vixdata.io/pd/jpg-large/es/sites/default/files/imj/elgranchef/L/Los-10-postres-mas-ricos-del-mundo-.jpg")
    )
    var recetas: List<Receta> = listOf(
        Receta(0, "Hamburguesa de queso", 1, 15, "1-pz de pan para hamburguesa|2-cucharadas de mayonesa|2-pz de queso", "Poner todo adentro de los panes|Calentar|Disfrutar", "https://cocina-casera.com/wp-content/uploads/2016/11/hamburguesa-queso-receta.jpg", 1),
        Receta(0, "Taco de sal", 1, 2,"1-pz de Sal|10-gr de sal", "Poner la tortilla en una plato|hecharle la sal|Disfruta", "https://img.wattpad.com/01fd1ace67bfacdb603fcf03ce84ee852a520560/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f776174747061642d6d656469612d736572766963652f53746f7279496d6167652f72623265524c574751675f3056773d3d2d362e313565386133633765343233353031653133343838393139313239332e6a7067?s=fit&w=720&h=720",2),
        Receta(0, "HotCakes", 2, 20, "1000-ml de Leche|2-pz de Huevo|5-gr de sal", "Vertir todo en un bold|Vertir en sarten de hotcakes|Retirar del fuego, Servir y preparar al gusto", "https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2019/10/recetas-de-hot-cake-y-pan-para-el-desayuno.jpg", 5),
        Receta(0, "Chocomilk", 1, 5, "1000-ml de leche|2-cucharadas de Chokomilk", "Vetir todo en una licuadora|Revolver por 3 minutos|Servir y preparar al gusto", "https://d1uz88p17r663j.cloudfront.net/resized/df696bd7-6587-4f6a-b96a-45c3d00e1a68_untitled_1200_600.png", 5)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val database = AppDatabase.getDatabase(this)
        lifecycleScope.launch{
            listaTiposComida = database.tiposComida().getTipoComida()
            if(listaTiposComida.size <= 0){
                try {
                    database.tiposComida().insertTiposComida(tipos)
                    database.recetas().insertReceta(recetas)
                    listaTiposComida = database.tiposComida().getTipoComida()
                }catch (sqliteException: SQLiteException){
                    Toast.makeText(binding.root.context.applicationContext, "Fallo de conexion a la base de datos", Toast.LENGTH_SHORT).show()
                }
            }
            initRecycler()
        }
    }

    fun initRecycler(){
        binding.rvTiposComida.layoutManager = LinearLayoutManager(this)
        val adapter = TipoComidaAdapter(listaTiposComida)
        binding.rvTiposComida.adapter = adapter
    }
}