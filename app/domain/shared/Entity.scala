package domain.shared

trait Entity {
  val id: Option[java.util.UUID]

  override final def hashCode: Int = 31 * id.##

  override final def equals(that: Any): Boolean = that match {
    case that: Entity => id == that.id
    case _ => false
  }
}
