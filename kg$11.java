import com.google.common.base.Optional;

final class kg$11
  implements kf<Optional<arc>>
{
  public void a(em ☃, Optional<arc> ☃)
  {
    if (☃.isPresent()) {
      ☃.b(ajt.j((arc)☃.get()));
    } else {
      ☃.b(0);
    }
  }
  
  public Optional<arc> b(em ☃)
  {
    int ☃ = ☃.g();
    if (☃ == 0) {
      return Optional.absent();
    }
    return Optional.of(ajt.c(☃));
  }
  
  public ke<Optional<arc>> a(int ☃)
  {
    return new ke(☃, this);
  }
}
