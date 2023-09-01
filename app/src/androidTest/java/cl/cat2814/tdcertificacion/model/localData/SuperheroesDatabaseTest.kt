package cl.cat2814.tdcertificacion.model.localData

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class SuperheroesDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var superheroDao: SuperheroDao
    private lateinit var db: SuperheroesDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SuperheroesDatabase::class.java).build()
        superheroDao = db.getSuperheroesFromDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertSuperheroes_empty() = runBlocking {
        // Given
        val superheroList = listOf<SuperheroEntity>()

        // When
        superheroDao.insertSuperheroes(superheroList)

        // Then A
        val it = superheroDao.getSuperheroesListFromDatabase().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        superheroDao.getSuperheroesListFromDatabase().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}
