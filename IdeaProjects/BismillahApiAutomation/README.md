Framework menggunakan TestNG sebagai framework utama untuk menulis dan menjalankan test case.
Library API menggunakan RestAssured. Library ini dipakai untuk mengirim berbagai macam request HTTP (seperti GET, POST, PUT, DELETE) ke sebuah API dan untuk memvalidasi respons yang diterima.
JSON-Java dan JSON-Path untuk mem-parsing dan membaca data dari respons API.
GSON untuk mengubah data JSON menjadi Java Object dan sebaliknya, yang membuat kode lebih clean dan easy to manage.
Berikut adalah rincian setiap tes:
TestGetSingleUserById() Tes ini bertujuan untuk mengambil data satu pengguna spesifik berdasarkan ID-nya. Verifikasi yang dilakukan adalah memastikan respons dari server sukses (status 200), ID User yang diterima sesuai dengan yang diminta, dan data penting seperti email dan nama tidak kosong.
testGetListOfUsers() Tes ini berfungsi untuk mengambil List User dari halaman tertentu. Tes ini memastikan respons server sukses (status 200), nomor halaman yang diterima benar, dan yang terpenting, daftar data user tidak kosong.
testCreateNewUser() Tes ini menguji fungsionalitas pembuatan new user dengan mengirimkan data nama dan pekerjaan. Verifikasi utamanya adalah memastikan user berhasil dibuat (status 201), data yang tersimpan di server sesuai dengan yang dikirim, serta server berhasil menghasilkan ID dan waktu pembuatan untuk new user tersebut.