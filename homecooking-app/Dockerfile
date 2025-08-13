FROM node:current-alpine AS builder

WORKDIR /workdir

RUN npm install -g pnpm

COPY ./package.json /workdir/package.json
COPY ./pnpm-lock.yaml  /workdir/pnpm-lock.yaml

RUN pnpm install
RUN ls -l

COPY . /workdir

RUN pnpm run build


FROM nginx:stable

COPY ./docker/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /workdir/dist /var/www/
