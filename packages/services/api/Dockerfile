FROM node:10.14.1-alpine

WORKDIR /app
COPY . .
COPY ssl ./ssl
RUN npm install --production
EXPOSE 8080
CMD [ "npm","start" ]

# CMD npm start
