FROM node:20-alpine
WORKDIR /app
COPY package.json .
COPY yarn.lock .
RUN yarn install --silent
COPY . .
EXPOSE 8081
CMD ["yarn", "dev"]