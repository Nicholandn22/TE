package ac.id.ukdw.te;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class KalkulatorTest {
    private Kalkulator kal;
    // @BeforeAll: Dieksekusi sekali sebelum semua test dijalankan
 @BeforeAll
 static void setupAll() {
 System.out.println("===> Mulai pengujian Calculator BMI");
 }

 // @BeforeEach: Dieksekusi sebelum setiap metode @Test
 @BeforeEach
 void setup() {
 kal = new Kalkulator();
 System.out.println("--> Mulai satu pengujian");
 }

 // @Test: Metode pengujian biasa
@Test
 void testPenjumlahan() {
 assertEquals(5, kal.tambah(2, 3));
 }

 @Test
 void testPembagian() {
 assertEquals(3, kal.bagi(9, 3));
 }

  // @AfterEach: Dieksekusi setelah setiap metode @Test
  @AfterEach
  void tearDown() {
  System.out.println("--> Selesai satu pengujian");
  }

  // @AfterAll: Dieksekusi sekali setelah semua test selesai
  @AfterAll
  static void tearDownAll() {
  System.out.println("===> Selesai pengujian Calculator");
  }
//  @Test
//  void testPembagian() {
//  assertEquals(10/3, kal.bagi(10, 3));
//  }
//  // @Disabled: Menonaktifkan pengujian sementara
//  @Disabled("Masih dalam pengembangan")
//  @Test
//  void testYangBelumSelesai() {
//  fail("Tes ini seharusnya tidak dijalankan");
 }




