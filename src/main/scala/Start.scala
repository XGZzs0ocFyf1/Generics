
class Pet(id: Long, name: String, medicalDataId: Long)

case class Cat(id: Long, name: String, medicalDataId: Long, disposition: String) extends Pet(id, name, medicalDataId)
case class Dog(id: Long, name: String, medicalDataId: Long, weight: Double) extends Pet(id, name, medicalDataId)



case class MedicalData(medicalDataId: Long, data: String)

class PetDto(id: Long, name: String, data: MedicalData)

case class CatDto(id: Long, name: String, medicalDataId: MedicalData, disposition: String) extends PetDto(id, name, medicalDataId)
case class DogDto(id: Long, name: String, medicalDataId: MedicalData, weight: Double) extends PetDto(id, name, medicalDataId)


object Conversions{
  def toDto[A <: Pet](in: A): PetDto with Product = {
    in.getClass.getSimpleName match {
      case "Cat" =>
        val catInstance = in.asInstanceOf[Cat]
        println(s"[CAT] this is ${catInstance}")
        CatDto(catInstance.id, catInstance.name, MedicalData(1, "здоров"), catInstance.disposition)

      case "Dog" =>
        val dogInstance = in.asInstanceOf[Dog]
        println(s"[DOG] this is ${dogInstance}")
        DogDto(dogInstance.id, dogInstance.name, MedicalData(2, "здоров и бодр"), dogInstance.weight)
      case _ => throw new RuntimeException("Unknown pet type")
    }
  }
}


object Start extends App{
  import Conversions._
  val barsik =  Cat(1,"Барсик", 1, "добрый нрав")
  val marly =  Dog(1,"Марли", 1, 10)

  val barsikDto = toDto(barsik)
  val marlyDto = toDto(marly)

  println(barsikDto)
  println(marlyDto)


}
