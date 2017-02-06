package net.minecraftforge.client.model;

import com.google.common.base.Optional;

public abstract interface IModelState
{
  public abstract Optional<TRSRTransformation> apply(Optional<? extends IModelPart> paramOptional);
}
