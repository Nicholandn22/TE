package ac.id.ukdw.te;

public class BMIService {
    // Menghitung BMI berdasarkan berat badan dan tinggi badan
    // public static double hitungBMI(double beratBadan, double tinggiBadan) {
    // if (beratBadan <= 0 || tinggiBadan <= 0) {
    // throw new IllegalArgumentException("Berat badan dan tinggi badan harus lebih besar dari 0");
    // }
    // return beratBadan / (tinggiBadan * tinggiBadan);
    // }


    // kode yang diperbarui
    // Menghitung BMI berdasarkan berat badan dan tinggi badan
    public static double hitungBMI(double beratBadan, double tinggiBadan) {
        // Batas minimum dan maksimum manusia normal
        if (beratBadan < 2.5) {
            throw new IllegalArgumentException("Berat dibawah minimal seorang manusia normal");
        } else if (beratBadan > 300) {
            throw new IllegalArgumentException("Berat diatas maksimal seorang manusia normal");
        } else if (tinggiBadan < 0.45) {
            throw new IllegalArgumentException("Tinggi dibawah minimal seorang manusia normal");
        } else if (tinggiBadan > 2.5) {
            throw new IllegalArgumentException("Tinggi diatas maksimal seorang manusia normal");
        }

        if (beratBadan <= 0 || tinggiBadan <= 0) {
            throw new IllegalArgumentException("Berat badan dan tinggi badan harus lebih besar dari 0");
        }

        return beratBadan / (tinggiBadan * tinggiBadan);
    }

    


    // Mengklasifikasikan BMI
    public static String klasifikasiBMI(double bmi) {
    if (bmi < 18.5) {
    return "Kurang berat badan";
    } else if (bmi < 24.9) {
    return "Normal";
    } else if (bmi < 29.9) {
    return "Kelebihan berat badan";
    } else {
    return "Obesitas";
    }
    }
   }
   