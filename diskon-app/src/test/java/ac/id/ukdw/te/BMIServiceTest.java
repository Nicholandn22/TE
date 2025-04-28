package ac.id.ukdw.te;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BMIServiceTest {

    @BeforeAll
    static void setupAll() {
        System.out.println("===> Mulai pengujian BMIService Calculator");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("===> Selesai pengujian BMIService Calculator");
    }

    @BeforeEach
    void setup() {
        System.out.println("--> Mulai satu pengujian");
    }

    @AfterEach
    void tearDown() {
        System.out.println("--> Selesai satu pengujian");
    }

    // 1. Test berat dan tinggi di bawah dan di atas batas manusia normal
    @ParameterizedTest(name = "Berat: {0}, Tinggi: {1} → Error: {2}")
    @CsvSource({

        
        // test untuk mengecek apakah berat dan tinggi itu didalam batas manusia normal
        //berat Minimal dan Tinggi manusia normal : 2.5kg dan 300kg
        //tinggi Minimal dan Tinggi manusia normal : 0.45M dan 2.5M

        "2.0, 1.5, Berat dibawah minimal seorang manusia normal",         // Berat di bawah minimal

        "60.0, 0.4, Tinggi dibawah minimal seorang manusia normal",        // Tinggi di bawah minimal

        "301.0, 1.8, Berat diatas maksimal seorang manusia normal",        // Berat di atas maksimal

        "70.0, 2.6, Tinggi diatas maksimal seorang manusia normal"        // Tinggi di atas maksimal
    })
    void testBeratTinggiDiLuarBatas(double berat, double tinggi, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BMIService.hitungBMI(berat, tinggi);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }


    // 2. Parameterized test untuk nilai valid dengan EP dan BVA
    @ParameterizedTest(name = "Berat: {0} kg, Tinggi: {1} m → BMI: {2}")
    @CsvSource({
        // EP
        "45, 1.6, 17.58", // Kurang berat badan
        "60, 1.7, 20.76", // Normal
        "85, 1.75, 27.76", // Kelebihan berat badan
        "95, 1.65, 34.9", // Obesitas

        // BVA
        "50, 2.5, 8.0", // Tinggi maksimal manusia
        "300, 2.0, 75.0", // batas atas "Obesitas" serta batas maksimum Berat badan manusia normal
        "18.4, 1.0, 18.4", // batas atas "Kurang Berat Badan"
        "18.5, 1.0, 18.5", // batas bawah "Normal"
        "24.9, 1.0, 24.9", //  batas atas "Normal"
        "25, 1.0, 25", // batas bawah "Kelebihan berat badan"
        "29.9, 1.0, 29.9", // batas atas "Kelebihan berat badan"
        "30, 1.0, 30", // batas bawah "Obesitas"

        
        "2.5, 1.0, 2.5",  // Berat  minimum valid
        "2.5, 0.45, 12.35",  // tinggi minimum valid
        "300, 1.0, 300",  // Berat maksimum valid
        "300, 2.5, 48.0"     // tinggi maksimum valid
    })
    void testHitungBMI(double berat, double tinggi, double expectedBMI) {
        Assumptions.assumeTrue(berat > 0 && tinggi > 0, "Tes dihentikan karena berat/tinggi 0");
        double result = BMIService.hitungBMI(berat, tinggi);
        assertEquals(expectedBMI, Math.round(result * 100.0) / 100.0, 0.01);
    }

    // 3. Test exception jika berat atau tinggi negatif
    @ParameterizedTest(name = "Berat negatif: {0}, Tinggi: {1}")
    @CsvSource({
        "-60, 1.7",
        "60, -1.7",
        "-60, -1.7"
    })
    void testNegatifThrowsException(double berat, double tinggi) {
        assertThrows(IllegalArgumentException.class, () -> {
            BMIService.hitungBMI(berat, tinggi);
        });
    }

    // 4. Test dengan nilai 0 menggunakan Assumption
    @ParameterizedTest(name = "Berat: {0}, Tinggi: {1} (0 Detected)")
    @CsvSource({
        "0, 1.7",
        "60, 0",
        "0, 0"
    })
    void testZeroValuesSkip(double berat, double tinggi) {
        Assumptions.assumeTrue(false, "Tes dihentikan karena berat/tinggi bernilai 0");
    }
}

