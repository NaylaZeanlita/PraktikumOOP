import java.util.*;

class admin {
    private String usn_adm, pass, nama;
    private int no_adm;
    
    public admin(String usn_adm, String pass, String nama, int no_adm) {
        this.usn_adm = usn_adm;
        this.pass = pass;
        this.nama = nama;
        this.no_adm = no_adm;
    }

    public String getUsn_adm() {
        return usn_adm;
    }
    
    public void setUsn_adm(String usn_adm) {
        this.usn_adm = usn_adm;
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public int getNo_adm() {
        return no_adm;
    }
    
    public void setNo_adm(int no_adm) {
        this.no_adm = no_adm;
    }
    
    public void tampilProfil() {
        System.out.println("Admin ID: " + no_adm);
        System.out.println("username: " + usn_adm);
        System.out.println("nama: " + nama);
    }
}

class LoginAdmin {
    private final ArrayList<admin> adminList;
    private admin currentAdmin;
    private final Scanner scanner;
    private boolean loggedIn;
    
    public LoginAdmin() {
        adminList = new ArrayList<>();
        // Admin bawaan
        adminList.add(new admin("admin", "admin123", "Nayla", 1));
        scanner = new Scanner(System.in);
        loggedIn = false;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public boolean login() {
        System.out.println("\n=== Admin Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        for (admin a : adminList) {
            if (a.getUsn_adm().equals(username) && a.getPass().equals(password)) {
                currentAdmin = a;
                loggedIn = true;
                System.out.println("Login Berhasil! Selamat Datang, " + a.getNama());
                return true;
            }
        }
        
        System.out.println("Username atau password salah, silahkan coba lagi.");
        return false;
    }
    
    public void logout() {
        currentAdmin = null;
        loggedIn = false;
        System.out.println("Anda Keluar.");
    }
    
    public static void shoesCareMenu() {
        System.out.println("\n=== ShoesCare ===");
        System.out.println("1. Manajemen Admin");
        System.out.println("2. Manajemen Layanan");
        System.out.println("3. Daftar Jenis Cucian");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public void manajemenAdmin() {
        boolean adminMenuRunning = true;
    
        while (adminMenuRunning && loggedIn) {
            System.out.println("\n=== Manajemen Admin ===");
            System.out.println("1. Profil Anda");
            System.out.println("2. Tambah Admin");
            System.out.println("3. Daftar Admin");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu: ");
            
            int pilih;
            try {
                pilih = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid");
                continue;
            }
            
            switch (pilih) {
                case 1 -> profilAdmin();
                case 2 -> tambahAdmin();
                case 3 -> daftarAdmin();
                case 4 -> adminMenuRunning = false;
                default -> System.out.println("Pilihan tidak valid, silahkan coba lagi.");
            }
        }
    }
    
    private void profilAdmin() {
        System.out.println("\n=== Profil Anda ===");
        currentAdmin.tampilProfil();
    }
    
    private void tambahAdmin() {
        System.out.println("\n=== Tambah Admin ===");
        System.out.print("username: ");
        String username = scanner.nextLine();
        
        for (admin a : adminList) {
            if (a.getUsn_adm().equals(username)) {
                System.out.println("Username telah terpakai, silahkan buat username lain.");
                return;
            }
        }
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("nama: ");
        String nama = scanner.nextLine();
        
        int newId = adminList.size() + 1;
        
        admin newAdmin = new admin(username, password, nama, newId);
        adminList.add(newAdmin);
        
        System.out.println("Berhasil menambahkaan admin");
    }
    
    private void daftarAdmin() {
        System.out.println("\n=== Daftar Admin ===");
        if (adminList.isEmpty()) {
            System.out.println("Admin tidak ada");
            return;
        }
    
        System.out.println("+" + "-".repeat(48) + "+");
        System.out.printf("| %-5s | %-15s | %-20s |%n", "ID", "Username", "Nama");
        System.out.println("+" + "-".repeat(48) + "+");
    
        for (admin a : adminList) {
            System.out.printf("| %-5d | %-15s | %-20s |%n", 
                a.getNo_adm(),   
                a.getUsn_adm(),  
                a.getNama()      
            );
        }

        System.out.println("+" + "-".repeat(48) + "+");
    }
}

// Kelas Induk
class Cucian {
    protected int id;
    protected String namaLayanan;
    protected int harga;
    
    public Cucian(int id, String namaLayanan, int harga) {
        this.id = id;
        this.namaLayanan = namaLayanan;
        this.harga = harga;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNamaLayanan() {
        return namaLayanan;
    }
    
    public int getHarga() {
        return harga;
    }
}

// subclass Cucian
class pilihanCucian extends Cucian {
    String jenisCucian;
    int hargaCucian;

    public pilihanCucian(int id, String jenisCucian, int hargaCucian) {
        super(id, jenisCucian, hargaCucian);
        this.jenisCucian = jenisCucian;
        this.hargaCucian = hargaCucian;
    }
}

// Kelas induk
class Transaksi {
    protected int idTransaksi;
    protected String tanggalTransaksi;
    
    public Transaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
        this.tanggalTransaksi = new java.util.Date().toString();
    }
    
    public int getIdTransaksi() {
        return idTransaksi;
    }
}

// Subclass transaksi
class Layanan extends Transaksi {
    static int counter = 1;
    int noPesanan;
    String jenisSepatu;
    int jumlahSepatu;
    pilihanCucian jenisCucian;
    int hargaLayanan;
    String status;

    public Layanan(String jenisSepatu, int jumlahSepatu, pilihanCucian jenisCucian) {
        super(counter);
        this.noPesanan = counter++;
        this.jenisSepatu = jenisSepatu;
        this.jumlahSepatu = jumlahSepatu;
        this.jenisCucian = jenisCucian;
        this.hargaLayanan = jenisCucian.hargaCucian * jumlahSepatu;
        this.status = "Proses";
    }

    public int getjumlahSepatu() {
        return jumlahSepatu;
    }

    public Cucian getjenisCucian() {
        return jenisCucian;
    }
}

// subclass transaksi
class Pembayaran extends Transaksi {
    int noPesanan;
    int totalHarga;

    public Pembayaran(Layanan layanan) {
        super(layanan.noPesanan);
        this.noPesanan = layanan.noPesanan;
        this.totalHarga = layanan.hargaLayanan;
    }

    public int getnoPesanan() {
        return noPesanan;
    }
}


public class app {
    static List<pilihanCucian> daftarCucian = new ArrayList<>();
    static List<Layanan> daftarLayanan = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initpilihanCucian();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Login Admin");
            System.out.println("2. Pengunjung (Lihat Layanan & Pembayaran)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> menuAdmin();
                case 2 -> tampilkanLayanan();
                case 3 -> {
                    running = false;
                    System.out.println("Terima kasih telah menggunakan ShoesCare!");
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void menuAdmin() {
        LoginAdmin loginAdmin = new LoginAdmin();
        
        if (loginAdmin.login()) {
            boolean running = true;
            
            while (running) {
                LoginAdmin.shoesCareMenu();
                int pilih;
                
                try {
                    pilih = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Masukkan angka yang valid.");
                    continue;
                }
                
                switch (pilih) {
                    case 1 -> loginAdmin.manajemenAdmin(); 
                    case 2 -> manajemenLayanan(); 
                    case 3 -> tampilkanCucian(); 
                    case 4 -> { 
                        running = false;
                        loginAdmin.logout();
                    }
                    default -> System.out.println("Pilihan tidak ada, silahkan coba lagi.");
                }
            }
        }
    }  

    static void initpilihanCucian() {
        daftarCucian.add(new pilihanCucian(1, "Fast Clean", 20000));
        daftarCucian.add(new pilihanCucian(2, "Deep Clean", 40000));
        daftarCucian.add(new pilihanCucian(3, "Premium Clean", 60000));
    }

    static void manajemenLayanan() {
        boolean layananRunning = true;
        while (layananRunning) {
            System.out.println("\n=== Manajemen Layanan ===");
            System.out.println("1. Tambah Layanan");
            System.out.println("2. Update Status Layanan");
            System.out.println("3. Hapus Layanan");
            System.out.println("4. Lihat Daftar Layanan");
            System.out.println("5. Kembali");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahLayanan();
                case 2 -> updateStatusLayanan();
                case 3 -> hapusLayanan();
                case 4 -> tampilkanLayanan();
                case 5 -> layananRunning = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tambahLayanan() {
        System.out.print("Masukkan jenis sepatu: ");
        String jenisSepatu = scanner.nextLine();
        System.out.print("Masukkan jumlah sepatu: ");
        int jumlahSepatu = scanner.nextInt();
        scanner.nextLine();
        tampilkanCucian();
        System.out.print("Pilih jenis cucian (1-3): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        if (pilihan >= 1 && pilihan <= 3) {
            pilihanCucian cucianDipilih = daftarCucian.get(pilihan - 1);
            Layanan layananBaru = new Layanan(jenisSepatu, jumlahSepatu, cucianDipilih);
            daftarLayanan.add(layananBaru);
            System.out.println("Layanan berhasil ditambahkan!");
        } else {
            System.out.println("Pilihan tidak valid!");
        }
    }

    static void updateStatusLayanan() {
        tampilkanLayanan();
        System.out.print("Masukkan nomor pesanan: ");
        int noPesanan = scanner.nextInt();
        scanner.nextLine();
        for (Layanan layanan : daftarLayanan) {
            if (layanan.noPesanan == noPesanan) {
                System.out.print("Ubah status (Proses/Selesai): ");
                layanan.status = scanner.nextLine();
                System.out.println("Status berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Pesanan tidak ditemukan!");
    }

    static void hapusLayanan() {
        tampilkanLayanan();
        System.out.print("Masukkan nomor pesanan yang ingin dihapus: ");
        int noPesanan = scanner.nextInt();
        scanner.nextLine();
        daftarLayanan.removeIf(layanan -> layanan.noPesanan == noPesanan);
        System.out.println("Layanan berhasil dihapus!");
    }

    static void tampilkanLayanan() {
        System.out.println("\n=== Daftar Layanan ===");
        if (daftarLayanan.isEmpty()) {
            System.out.println("Belum ada layanan.");
            return;
        }

        System.out.println("+" + "-".repeat(83) + "+");
        System.out.printf("| %-5s | %-15s | %-10s | %-15s | %-10s | %-10s |%n","No", "Jenis Sepatu", "Jumlah", "Jenis Cucian", "Status", "Total Harga");
        System.out.println("+" + "-".repeat(83) + "+");

        for (Layanan layanan : daftarLayanan) {
            Pembayaran pembayaran = new Pembayaran(layanan);
            System.out.printf("| %-5d | %-15s | %-10d | %-15s | %-10s | Rp%-9d |%n", layanan.noPesanan, layanan.jenisSepatu, layanan.jumlahSepatu, layanan.jenisCucian.jenisCucian, layanan.status, pembayaran.totalHarga);
        }

        System.out.println("+" + "-".repeat(83) + "+");
    }


    static void tampilkanCucian() {
        System.out.println("\n=== Jenis Cucian ===");
        for (pilihanCucian pilihancucian : daftarCucian) {
            System.out.println(pilihancucian.id + ". " + pilihancucian.jenisCucian + " - Rp" + pilihancucian.hargaCucian);
        }
    }
}

