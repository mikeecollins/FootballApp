package org.example.persistence

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import org.example.models.Player
import org.example.models.Team
import persistence.Serializer
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class XMLSerializer ( private val file: File) : Serializer {

    @Throws(Exception::class)
    override fun read(): Any {
            val xStream = XStream(DomDriver())
            xStream.allowTypes(arrayOf(Player::class.java))
            xStream.allowTypes(arrayOf(Team::class.java))
            val inputStream = xStream.createObjectInputStream(FileReader(file))
            val obj = inputStream.readObject() as Any
            inputStream.close()
            return obj
    }

    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(DomDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }
}

