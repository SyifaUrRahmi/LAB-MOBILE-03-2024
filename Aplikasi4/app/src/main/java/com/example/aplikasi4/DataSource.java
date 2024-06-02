package com.example.aplikasi4;

import java.util.ArrayList;

// Deklarasi kelas DataSource
public class DataSource {

    // Deklarasi variabel statis instagrams yang berisi ArrayList Instagram
    public static ArrayList<Instagram> instagrams = generateDummyInstagrams();

    // Method statis generateDummyInstagrams untuk menghasilkan data dummy Instagram
    private static ArrayList<Instagram> generateDummyInstagrams() {
        // Inisialisasi ArrayList Instagram
        ArrayList<Instagram> instagrams1 = new ArrayList<>();

        // Menambahkan data dummy Instagram ke dalam ArrayList
        instagrams1.add(new Instagram("Bowo","Prabowo_Subianto"
                ,"Menang kalah adalah hal yang biasa, jangankan menang dan kalah Mati Pun Kami Siap. \n#Presiden Republik Indonesia ke-8"
                ,R.drawable.bowo,R.drawable.bowo));

        instagrams1.add(new Instagram("Gue Conniee", "Connie Bakrie"
                ,"Pengamat Militer Pertahanan dan Keamanan \n#Strong Woman"
                ,R.drawable.connie,R.drawable.connie));

        instagrams1.add(new Instagram("Obama_JR", "Barack Husein Obama "
                ,"Presiden ke-44 Amerika Serikat dan Presiden kulit hitam pertama di US \n#US President"
                ,R.drawable.obama, R.drawable.obama));

        instagrams1.add((new Instagram("Putin_Rusia","Vladimir Putin"
                ,"Presiden dengan masa Jabatan terlama kedua di Eropa setelah Aleksandr Lukashenko, Presiden Belarus.  Putin telah menjadi Presiden Rusia sejak 7 Mei 2012 \n#Presiden ke-4 Rusia "
                ,R.drawable.putin,R.drawable.putin)));

        instagrams1.add(new Instagram("Rocky!", "Rocky Gerung"
                ,"Presiden Akal Sehat, \nIjazah adalah tanda seseorang pernah bersekolah bukan tanda pernah berpikir \n#No Rocky No Party"
                ,R.drawable.rocky,R.drawable.rocky));

        instagrams1.add(new Instagram("Nana","Najwa Shihab"
                ,"Hidup yang tidak dipertaruhkan, Tidak akan pernah Dimenangkan! \n#Perempuan Indonesia"
                ,R.drawable.shihab, R.drawable.shihab));

        instagrams1.add(new Instagram("Suharto","Presiden Suharto"
                , "Biarlah sejarah yang mencatat, dengan hati bersih saya sudah memimpin dan memajukan negeri ini \n#Enak Jamanku Tohh"
                ,R.drawable.suharto,R.drawable.suharto));

        instagrams1.add(new Instagram("Ratu Laut","Susi Pudjiastuti"
                ," untuk memulai suatu usaha apapun bentuknya, para perempuan harus percaya diri. Jangan pernah berpikir dan menyatakan tidak bisa sebelum memulai. Jika seperti itu, secara tidak langsung mereka akan membatasi diri. Women do things better. \n#Ledakkan"
                ,R.drawable.susi, R.drawable.susi));

        instagrams1.add(new Instagram(" Presiden SBY", "Susilo Bambang Yudhoyono"
                ,"Kawal terus Demokrasi, Sejarah Sedang diukir Kembali \n#" +
                "Presiden ke-6 Indonesia"
                ,R.drawable.susilo, R.drawable.susilo));

        instagrams1.add(new Instagram("Prof Yusril", "Yusril Ihza Mahendra"
                ,"Kalo saya ngotot waktu itu,  Amin Rais dan Megawati tidak melakukan manipulasi, maka saya Presiden Indonesia secara aklamasi \n#Hukum Tata Negara"
                ,R.drawable.yusril, R.drawable.yusril));

        // Mengembalikan ArrayList Instagram yang berisi data dummy
        return instagrams1;
    }
}

