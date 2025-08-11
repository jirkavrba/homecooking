FROM oven/bun:1.2.20-alpine

COPY package.json ./
COPY bun.lock ./
COPY src ./

RUN bun install

ENTRYPOINT ["bun", "run", "index.ts"]
