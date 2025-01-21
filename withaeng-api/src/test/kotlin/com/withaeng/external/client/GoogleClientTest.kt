//package com.withaeng.external.client
//
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class GoogleClientTest{
//    @Autowired
//    private lateinit var googleClient: GoogleClient
//
//    @Test
//    fun test(){
//        val aa = googleClient.getToken("4%2F0AanRRruqURbjUmH6JVi3y-9qrz8tM00x5VzdSgh27bUnK-OhFhrEd06U27_ElCh5Dn4NnA")
//        println("googleClient.getToken" +  aa)
//        val bb = googleClient.getMe(aa?.accessToken!!)
//        val cc = googleClient.getInfo(aa?.accessToken!!)
//        println("googleClient.getMe" +  bb)
//        println("googleClient.getInfo" +  cc)
//    }
//}