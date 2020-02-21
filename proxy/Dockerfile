FROM node:9.11.1-alpine

WORKDIR /app

COPY . ./

RUN npm install

EXPOSE 8082

ENTRYPOINT ["node", "./app.js"]
