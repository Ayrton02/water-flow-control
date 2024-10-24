package core.usecase;

public interface BaseUseCase <I, O> {
   O execute(I input);
}
