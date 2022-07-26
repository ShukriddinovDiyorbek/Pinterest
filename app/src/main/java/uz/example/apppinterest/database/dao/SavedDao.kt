package uz.example.apppinterest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.example.apppinterest.database.Saved

@Dao
interface SavedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(saved: Saved)

    @Query("SELECT * FROM Saved")
    fun getSaved(): List<Saved>

    @Query("SELECT COUNT() FROM Saved WHERE savedID = :id")
    fun count(id: String): Int

    @Query("DELETE FROM Saved")
    fun clearSaved()
}