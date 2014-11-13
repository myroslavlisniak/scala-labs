package org.scalalabs.basic.lab03
import sys._
/**
 * This exercise introduces you to the powerful pattern matching features of Scala.
 *
 * Pattern matching can in its essence be compared to Java's 'switch' statement,
 * even though it provides many more possibilites. Whereas the Java switch statmenet
 * lets you 'match' primitive types up to int's, Scala's pattern matching goes much
 * further. Practically everything from all types of objects and Collections
 * can be matched, not forgetting xml and a special type of class called case classes.
 *
 * Pattern matching is also often used in combination with recursive algorithms.
 *
 * For this exercise exclusively use pattern matching constructs in order to make the
 * corresponding unit test work.
 *
 * Reference material to solve these exercises can be found here:
 * Pattern matching in general: http://programming-scala.labs.oreilly.com/ch03.html#PatternMatching
 * Pattern matching in combination with partial functions: http://programming-scala.labs.oreilly.com/ch08.html#PartialFunctions
 */

object PatternMatchingExercise {

  /*************************************************************************
   *  pattern matching exercises
   * For expected solution see unittest @PatternMatchingExerciseTest
   *************************************************************************/

  def describeLanguage(s: String) = {
    s match {
      case "Java" => "OOP"
      case "Smalltalk" => "OOP"
      case "Clojure" => "Functional"
      case "Haskell" => "Functional"
      case "C" => "Procedural"
      case "Scala" => "Hybrid"
      case _ => "Unknown"
    }
  }

  def matchOnInputType(in: Any) = {
    in match {
      case str: String => "A string with length " + str.size
      case n: Int if n > 0 => "A positive integer"
      case Person(n, age) => s"A person with name: $n"
      case seq: Seq[Any] if seq.size > 10 => "Seq with more than 10 elements"
      case seq: Seq[Any] if seq.size > 3 => {
        val first = seq.head
        val second = seq.tail.head
        val rest = seq.tail.tail
        s"first: $first, second: $second, rest: $rest"
      }
      case opt : Option[Any] => "A Scala Option subtype"
      case null =>  "A null value"
      case _ =>  "Some Scala class"
    }
  }

  def older(p: Person): Option[String] = {
    p match {
      case Person(name, age) if age > 30 => Some(name)
      case _ => None
    }
  }

  /*************************************************************************
   * Pattern matching with partial functions
   * For expected solution see @PatternMatchingExerciseTest
   *************************************************************************/

  val pf1: PartialFunction[String, String] = {
    case "scala-labs" => "something"
    case "stuff" => "another"
  }

  val pf2: PartialFunction[String, String] = {
    case "other stuff" => error("fix me")
  }

  val pf3:PartialFunction[String, String] = {
    case "scala-labs" => "something"
    case "other stuff" =>"fix me"
  }

}

case class Person(name: String, age: Int)