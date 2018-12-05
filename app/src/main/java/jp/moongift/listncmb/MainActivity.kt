package jp.moongift.listncmb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nifcloud.mbaas.core.NCMB
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import com.nifcloud.mbaas.core.*

const val applicationKey:String = "APPLICATION_KEY"
const val clientKey:String = "CLIENT_KEY"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初期化
        NCMB.initialize(applicationContext, applicationKey, clientKey)

        val query = NCMBQuery<NCMBObject>("TestClass")
        query.findInBackground { objects, e ->
            if (e != null) {
                // エラー
                Log.d("[Error]", e.toString())
            } else {
                // 取得
                var items = arrayListOf<String>()
                for (obj in objects) {
                    items.add(obj.getString("message"))
                }
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
                listView.adapter = adapter
            }
        }
    }
}
