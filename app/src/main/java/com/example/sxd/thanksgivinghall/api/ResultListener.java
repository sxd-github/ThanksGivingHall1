package com.example.sxd.thanksgivinghall.api;

public abstract interface ResultListener<T>
{
    public abstract void onEnd();

    public abstract void onFailure(String paramString);

    public abstract void onStart();

    public abstract void onSuccess(T paramT);
}