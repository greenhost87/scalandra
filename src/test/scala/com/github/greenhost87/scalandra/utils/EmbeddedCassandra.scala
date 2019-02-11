package com.github.greenhost87.scalandra.utils

import java.lang.instrument.{ClassDefinition, ClassFileTransformer, Instrumentation}
import java.util.jar.JarFile

import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.github.jamm.MemoryMeter

import scala.util.{Failure, Try}

object EmbeddedCassandra {

  // Hack jamm :)
  val instrumentation: Instrumentation = {
    new Instrumentation {
      override def getObjectSize(objectToSize: scala.Any): Long = {
        1000L
      }

      override def redefineClasses(definitions: ClassDefinition*): Unit = ???

      override def removeTransformer(transformer: ClassFileTransformer): Boolean = ???

      override def getInitiatedClasses(loader: ClassLoader): Array[Class[_]] = ???

      override def retransformClasses(classes: Class[_]*): Unit = ???

      override def isNativeMethodPrefixSupported: Boolean = ???

      override def getAllLoadedClasses: Array[Class[_]] = ???

      override def isRetransformClassesSupported: Boolean = ???

      override def appendToSystemClassLoaderSearch(jarfile: JarFile): Unit = ???

      override def setNativeMethodPrefix(transformer: ClassFileTransformer, prefix: String): Unit = ???

      override def isModifiableClass(theClass: Class[_]): Boolean = ???

      override def addTransformer(transformer: ClassFileTransformer, canRetransform: Boolean): Unit = ???

      override def addTransformer(transformer: ClassFileTransformer): Unit = ???

      override def appendToBootstrapClassLoaderSearch(jarfile: JarFile): Unit = ???

      override def isRedefineClassesSupported: Boolean = ???
    }
  }

  MemoryMeter.premain("", instrumentation)

  def start(): Unit = Try(EmbeddedCassandraServerHelper.startEmbeddedCassandra(100000L)) match {
    case Failure(cause) =>
      throw new RuntimeException("Cassandra doesn't start", cause)
    case _ =>
      println("EmbeddedCassandra is started")
  }

  def cleanUp(): Unit = {
    Try(EmbeddedCassandraServerHelper.cleanEmbeddedCassandra()) match {
      case Failure(cause) =>
        println("EmbeddedCassandra clean-up fails: " + cause.getMessage) // ignore
      case _ =>
        println("EmbeddedCassandra is clean again")
    }
  }

  def keyspaceName(): String = Haiku.haiku;
}
