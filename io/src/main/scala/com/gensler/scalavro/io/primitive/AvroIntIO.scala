package com.gensler.scalavro.io.primitive

import com.gensler.scalavro.io.AvroTypeIO
import com.gensler.scalavro.types.primitive.AvroInt
import com.gensler.scalavro.error.{ AvroSerializationException, AvroDeserializationException }

import org.apache.avro.io.{ EncoderFactory, DecoderFactory, BinaryEncoder }

import scala.util.{ Try, Success, Failure }
import scala.reflect.runtime.universe.TypeTag

import java.io.{ InputStream, OutputStream }

object AvroIntIO extends AvroIntIO

trait AvroIntIO extends AvroTypeIO[Int] {

  def avroType = AvroInt

  protected[scalavro] def asGeneric[I <: Int: TypeTag](value: I): Int = value

  def write[I <: Int: TypeTag](value: I, encoder: BinaryEncoder) = {
    encoder writeInt value
    encoder.flush
  }

  def read(stream: InputStream) = Try {
    val decoder = DecoderFactory.get.directBinaryDecoder(stream, null)
    decoder.readInt
  }

}