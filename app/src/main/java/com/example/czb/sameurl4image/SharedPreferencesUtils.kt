package com.example.czb.sameurl4image

import android.content.Context

/**
 * SharedPreference方式持久化数据的工具类
 *
 * @date 2017/9/15 9:24
 * @version v4.0.0
 */

/** 默认，全局*/
const val SP_FILE_COMMON = "sp_common"

object SharedPreferencesUtils {

    /**
     * 保存键值对
     *
     * @param context  上下文
     * @param fileName  文件名
     * @param key  键
     * @param value  值
     *
     * @return 是否保存成功
     */
    fun save(context: Context, fileName: String = SP_FILE_COMMON, key: String,
             value: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    /**
     * 获得键对应的值,如果没有则返回""

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 值，没有则返回""
     */
    operator fun get(context: Context, fileName: String = SP_FILE_COMMON, key: String): String {
        return get(context, fileName, key, "")
    }

    /**
     * 获得键对应的值,如果没有则返回defaultValue

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @param defaultValue
     * *            默认值
     * *
     * @return 值，没有则返回defaultValue
     */
    operator fun get(context: Context, fileName: String = SP_FILE_COMMON, key: String, defaultValue: String): String {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val value = sharedPreferences.getString(key, defaultValue)// 第二个参数为默认值
        return value
    }

    /**
     * 移除一项

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 是否移除成功
     */
    fun remove(context: Context, fileName: String = SP_FILE_COMMON, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        return editor.commit()

    }

    /**
     * 保存键值对

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @param value
     * *            值
     * *
     * @return 是否保存成功
     */
    fun saveLong(context: Context, fileName: String = SP_FILE_COMMON, key: String,
                 value: Long): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        return editor.commit()
    }

    /**
     * 获得键对应的long值,如果没有则返回0

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 值，没有则返回0
     */
    fun getLong(context: Context, fileName: String = SP_FILE_COMMON, key: String): Long {
        return getLong(context, fileName, key, 0)
    }

    /**
     * 获得键对应的long值,如果没有则返回defaultValue

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @param defaultValue
     * *            默认值
     * *
     * @return 值，没有则返回defaultValue
     */
    fun getLong(context: Context, fileName: String = SP_FILE_COMMON, key: String, defaultValue: Long): Long {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val value = sharedPreferences.getLong(key, defaultValue)// 第二个参数为默认值
        return value
    }

    /**
     * 移除一项long值

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 是否移除成功
     */
    fun removeLong(context: Context, fileName: String = SP_FILE_COMMON, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        return editor.commit()

    }

    /**
     * 保存键值对

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @param value
     * *            值
     * *
     * @return 是否保存成功
     */
    fun saveInt(context: Context, fileName: String = SP_FILE_COMMON, key: String,
                value: Int): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        return editor.commit()
    }

    /**
     * 获得键对应的int值,如果没有则返回0

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 值，没有则返回0
     */
    fun getInt(context: Context, fileName: String = SP_FILE_COMMON, key: String): Int {
        return getInt(context, fileName, key, 0)
    }

    /**
     * 获得键对应的int值,如果没有则返回defaultValue

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @param defaultValue
     * *            默认值
     * *
     * @return 值，没有则返回defaultValue
     */
    fun getInt(context: Context, fileName: String = SP_FILE_COMMON, key: String, defaultValue: Int): Int {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val value = sharedPreferences.getInt(key, defaultValue)// 第二个参数为默认值
        return value
    }

    /**
     * 移除一项int值

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 是否移除成功
     */
    fun removeInt(context: Context, fileName: String = SP_FILE_COMMON, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        return editor.commit()

    }

    /**
     * 保存键值对

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @param value
     * *            值
     * *
     * @return 是否保存成功
     */
    fun saveBoolean(context: Context, fileName: String = SP_FILE_COMMON, key: String,
                    value: Boolean): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        return editor.commit()
    }

    /**
     * 获得键对应的int值,如果没有则返回defaultValue

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @param defaultValue
     * *            默认值
     * *
     * @return 值，没有则返回defaultValue
     */
    fun getBoolean(context: Context, fileName: String = SP_FILE_COMMON, key: String, defaultValue: Boolean = false):
            Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val value = sharedPreferences.getBoolean(key, defaultValue)// 第二个参数为默认值
        return value
    }

    /**
     * 移除一项int值

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 是否移除成功
     */
    fun removeBoolean(context: Context, fileName: String = SP_FILE_COMMON, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        return editor.commit()

    }

    /**
     * 清除文件内容

     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @return 是否清除成功
     */
    fun clear(context: Context, fileName: String = SP_FILE_COMMON): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        return editor.commit()

    }

    /**
     * 某一项是否存在
     *
     * @param context
     * *            上下文
     * *
     * @param fileName
     * *            文件名
     * *
     * @param key
     * *            键
     * *
     * @return 该键对应的值是否存在
     */
    fun contatins(context: Context, fileName: String = SP_FILE_COMMON, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE)
        return sharedPreferences.contains(key)

    }
}