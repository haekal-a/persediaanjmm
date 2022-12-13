package com.tamu.service.common;

public final class RepoQuery {
    public static final String GET_ALL_KUNJUNGAN =
            " select " +
                    " idKunjungan, " +
                    " namaWp, " +
                    " npwp, " +
                    " namaPegawai, " +
                    " nip, " +
                    " jabatan, " +
                    " unit, " +
                    " keperluan, " +
                    " status, " +
                    " keterangan, " +
                    " DATE_FORMAT(creation_date, '%d-%m-%Y') as tanggal " +
                    "from " +
                    " ( " +
                    " select " +
                    "  k.id idKunjungan, " +
                    "  m.nama_wp namaWp, " +
                    "  m.npwp_15 npwp, " +
                    "  p.nama namaPegawai, " +
                    "  p.nip, " +
                    "  p.jabatan, " +
                    "  p.unit, " +
                    "  k.keperluan, " +
                    "  case " +
                    "   when k.fg_status = '1' then 'Menunggu' " +
                    "   when k.fg_status = '2' then 'Selesai' " +
                    "   when k.fg_status = '3' then 'Batal' " +
                    "   else 'Tidak terdefinisi' " +
                    "  end status, " +
                    "  k.keterangan, " +
                    "  k.creation_date " +
                    " from " +
                    "  kunjungan k " +
                    " join mfwp m on " +
                    "  k.npwp = m.npwp_15  " +
                    " join pegawai p on " +
                    "  k.id_pegawai = p.id " +
                    "union " +
                    " select " +
                    "  k.id idKunjungan, " +
                    "  m.nama_wp namaWp, " +
                    "  m.npwp_15 npwp, " +
                    "  p.nama namaPegawai, " +
                    "  p.nip, " +
                    "  p.jabatan, " +
                    "  p.unit, " +
                    "  k.keperluan, " +
                    "  case " +
                    "   when k.fg_status = '1' then 'Menunggu' " +
                    "   when k.fg_status = '2' then 'Selesai' " +
                    "   when k.fg_status = '3' then 'Batal' " +
                    "   else 'Tidak terdefinisi' " +
                    "  end status, " +
                    "  k.keterangan, " +
                    "  k.creation_date " +
                    " from " +
                    "  kunjungan k " +
                    " join datawp m on " +
                    "  k.id_datawp = m.id " +
                    " join pegawai p on " +
                    "  k.id_pegawai = p.id ) a " +
                    "where " +
                    " (a.namaWp like :namaWp " +
                    "  or :namaWp is null) " +
                    " and (a.npwp = :npwp " +
                    "  or :npwp is null) " +
                    "  and (a.nip = :nip or :nip is null) " +
                    "  and (a.creation_date >= STR_TO_DATE(:tglAwal,'%d-%m-%Y') or :tglAwal is null) " +
                    "  and (a.creation_date <= STR_TO_DATE(:tglAkhir,'%d-%m-%Y') or :tglAkhir is null) " +
                    "  and (a.status = :status or :status is null) " +
                    "order by " +
                    " a.creation_date desc ";

}
