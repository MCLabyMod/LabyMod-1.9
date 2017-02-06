import com.google.common.base.Optional;

final class kg$3
  implements kf<Optional<cj>>
{
  public void a(em ☃, Optional<cj> ☃)
  {
    ☃.writeBoolean(☃.isPresent());
    if (☃.isPresent()) {
      ☃.a((cj)☃.get());
    }
  }
  
  public Optional<cj> b(em ☃)
  {
    if (!☃.readBoolean()) {
      return Optional.absent();
    }
    return Optional.of(☃.e());
  }
  
  public ke<Optional<cj>> a(int ☃)
  {
    return new ke(☃, this);
  }
}
