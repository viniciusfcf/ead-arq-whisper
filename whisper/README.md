Baseado no repo: https://github.com/containers/ai-lab-recipes/tree/main/model_servers/whispercpp


# Build da imagem base:
```podman build --squash-all --build-arg PORT=8001 -t quay.io/ai-lab/model_servers/whispercpp:latest . -f base/Containerfile```

# Download do modelo
```
cd models
curl -sLO https://huggingface.co/ggerganov/whisper.cpp/resolve/main/ggml-medium.bin
cd ..
```

# Run (nesta pasta, whisper)
```
podman run --rm -it \
        -p 8001:8001 \
        -v $(pwd)/models/ggml-medium.bin:/models/ggml-medium.bin \
        -e HOST=0.0.0.0 \
        -e MODEL_PATH=/models/ggml-medium.bin \
        -e PORT=8001 \
        quay.io/ai-lab/model_servers/whispercpp
```


# Openvino support (TODO)

https://github.com/ggerganov/whisper.cpp/blob/master/README.md#openvino-support