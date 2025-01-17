package com.example.uas_paba_klmpk6.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface balanceDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertExpense(expense: expense)

    @Query("UPDATE expense SET amount=:isi_amount, category=:isi_category, title=:isi_title, note=:isi_note, date=:isi_date, location=:isi_location WHERE id_expense=:pilih_id")
    fun updateExpense(isi_amount: Int, isi_category: String, isi_title: String, isi_note: String,isi_date: String, isi_location: String, pilih_id: Int)

    @Delete
    fun deleteExpense(expense: expense)

    @Query("SELECT * FROM expense ORDER BY id_expense asc")
    fun selectAllExpense() : MutableList<expense>

    @Query("SELECT * FROM expense WHERE id_expense=:isi_id")
    suspend fun getItemExpense(isi_id: Int) : expense


//    Untuk income
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIncome(income: income)

    @Query("UPDATE income SET amount=:isi_amount, category=:isi_category, title=:isi_title, note=:isi_note, date=:isi_date, location=:isi_location WHERE id_income=:pilih_id")
    fun updateIncome(isi_amount: Int, isi_category: String, isi_title: String, isi_note: String,isi_date: String, isi_location: String, pilih_id: Int)

    @Delete
    fun deleteIncome(income: income)

    @Query("SELECT * FROM income ORDER BY id_income asc")
    fun selectAllIncome() : MutableList<income>

    @Query("SELECT * FROM income WHERE id_income=:isi_id")
    suspend fun getItemIncome(isi_id: Int) : income

//idi wallet
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWallet(wallet: wallet)

    @Query("UPDATE wallet SET jumlah_balance=:isi_jumlah  WHERE id=:pilih_id")
    fun updateWallet(isi_jumlah: Int,pilih_id: Int)

    @Delete
    fun deleteWallet(wallet: wallet)

    @Query("SELECT * FROM wallet ORDER BY id asc")
    fun selectAllWallet() : MutableList<wallet>

    @Query("SELECT * FROM wallet WHERE id=:isi_id")
    suspend fun getItemWallet(isi_id: Int) : wallet

// untuk get semua
    @Query("""
    SELECT 'income' AS type, amount, category, title, note, date
    FROM income

    UNION ALL

    SELECT 'expense' AS type, amount, category, title, note, date
    FROM expense

    ORDER BY date ASC
""")
    fun getAllHistory(): MutableList<history>

    @Query("SELECT SUM(amount) FROM income")
    fun getTotalIncome(): Int

    @Query("SELECT SUM(amount) FROM expense")
    fun getTotalExpense(): Int
//    untuk template

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTemplate(templateInput: templateInput)

//    @Query("UPDATE templateInput SET amount=:isi_amount, category=:isi_category, title=:isi_title, note=:isi_note, date=:isi_date, location=:isi_location WHERE id_expense=:pilih_id")
//    fun updateTemplate(isi_amount: Int, isi_category: String, isi_title: String, isi_note: String,isi_date: String, isi_location: String, pilih_id: Int)

    @Delete
    fun deleteTemplate(templateInput: templateInput)

    @Query("SELECT * FROM templateInput ORDER BY id_template asc")
    fun selectAllTemplate() : MutableList<templateInput>

    @Query("SELECT * FROM templateInput WHERE id_template=:isi_id")
    suspend fun getItemTemplate(isi_id: Int) : templateInput


//    get untuk expense & income yang unik
//@Query("""
//    SELECT 'income' AS type, category, amount, title, note, date
//    FROM income
//    WHERE id_income IN (
//        SELECT MIN(id_income)
//        FROM income
//        GROUP BY category, amount
//        HAVING COUNT(*) = 1
//    )
//
//    UNION ALL
//
//    SELECT 'expense' AS type, category, amount, title, note, date
//    FROM expense
//    WHERE id_expense IN (
//        SELECT MIN(id_expense)
//        FROM expense
//        GROUP BY category, amount
//        HAVING COUNT(*) = 1
//    )
//""")
//    fun getTemplateInput(): MutableList<templateInput>
@Query("""
    SELECT * 
    FROM expense
    WHERE date BETWEEN :startDate AND :endDate
    ORDER BY date ASC
""")
fun getExpensesByDateRange(startDate: String, endDate: String): MutableList<expense>

    @Query("""
    SELECT * 
    FROM income
    WHERE date BETWEEN :startDate AND :endDate
    ORDER BY date ASC
""")
    fun getIncomesByDateRange(startDate: String, endDate: String): MutableList<income>

    @Query("""
    SELECT 'income' AS type, category, amount, title, note, date 
    FROM income 
    WHERE date BETWEEN :startDate AND :endDate 

    UNION ALL 

    SELECT 'expense' AS type, category, amount, title, note, date 
    FROM expense 
    WHERE date BETWEEN :startDate AND :endDate 
    ORDER BY date ASC
""")
    fun getAllHistoryByDateRange(startDate: String, endDate: String): MutableList<history>
}