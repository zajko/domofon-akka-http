package domofon

import domofon.tck.DomofonTck

class DomofonServiceTest extends DomofonTck with DomofonService {

  override def serverHostnameAndPort: String = "localhost:8080"

  override def tckAdminLogin: String = "admin"

  override def tckAdminPass: String = "password"
}
