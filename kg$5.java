import com.google.common.base.Optional;
import java.util.UUID;

final class kg$5
  implements kf<Optional<UUID>>
{
  public void a(em ☃, Optional<UUID> ☃)
  {
    ☃.writeBoolean(☃.isPresent());
    if (☃.isPresent()) {
      ☃.a((UUID)☃.get());
    }
  }
  
  public Optional<UUID> b(em ☃)
  {
    if (!☃.readBoolean()) {
      return Optional.absent();
    }
    return Optional.of(☃.i());
  }
  
  public ke<Optional<UUID>> a(int ☃)
  {
    return new ke(☃, this);
  }
}
