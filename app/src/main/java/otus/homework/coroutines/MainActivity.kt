package otus.homework.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var catsPresenter: CatsPresenter

    private val diContainer = DiContainer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = layoutInflater.inflate(R.layout.activity_main, null) as CatsView
        setContentView(view)

        catsPresenter = CatsPresenter(diContainer.service, diContainer.presenterScope)
        view.presenter = catsPresenter
        catsPresenter.attachView(view)
        catsPresenter.onInitComplete()
    }

    override fun onStop() {
        if (isFinishing) {
            catsPresenter.detachView()
        }
        catsPresenter.cancelCoroutine()
        super.onStop()
    }
}