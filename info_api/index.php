<?php

header('Content-Type: application/json');

$data = [
    ["nama" => "Android Coffee Point", "tanggalBerdiri" => "21 Juni 2023", 
    "deskripsi" => "Coffee Point, destinasi sempurna untuk menikmati kopi istimewa dengan kemudahan pemesanan online dan reservasi! Kami memahami bahwa kecepatan dan kenyamanan adalah kunci dalam menikmati hidangan favorit Anda. Oleh karena itu, kami dengan bangga menghadirkan layanan pemesanan online eksklusif kami. Sekarang Anda dapat menikmati kopi kesayangan Anda tanpa harus mengantri. Cukup kunjungi website kami atau unduh aplikasi kami yang mudah digunakan untuk memesan kopi favorit Anda dengan beberapa kali sentuhan jari. Namun, jika Anda ingin merasakan suasana kafe yang hangat dan menyenangkan, kami juga menyediakan layanan reservasi. Nikmati suasana santai dan duduk dengan nyaman di meja favorit Anda dengan memesan tempat terlebih dahulu. Jadikan Coffee Point sebagai tempat pertemuan yang sempurna, baik itu untuk rapat bisnis, acara keluarga, atau pertemuan teman"],
];

echo json_encode($data);