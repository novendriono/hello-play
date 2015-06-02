package object repository {
  def DB = play.api.db.DB
  implicit def current = play.api.Play.current
  implicit def global = scala.concurrent.ExecutionContext.Implicits.global
  def uuid = java.util.UUID.randomUUID.toString
}
