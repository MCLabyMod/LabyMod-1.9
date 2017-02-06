import com.google.common.collect.ComparisonChain;
import com.mojang.authlib.GameProfile;
import java.util.Comparator;

class bdp$a
  implements Comparator<bkv>
{
  public int a(bkv p_compare_1_, bkv p_compare_2_)
  {
    bbm scoreplayerteam = p_compare_1_.j();
    bbm scoreplayerteam1 = p_compare_2_.j();
    return ComparisonChain.start().compareTrueFirst(p_compare_1_.b() != ahw.a.e, p_compare_2_.b() != ahw.a.e).compare(scoreplayerteam != null ? scoreplayerteam.b() : "", scoreplayerteam1 != null ? scoreplayerteam1.b() : "").compare(p_compare_1_.a().getName(), p_compare_2_.a().getName()).result();
  }
}
