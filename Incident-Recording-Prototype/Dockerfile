FROM node:8.9.3
RUN mkdir -p /usr/src/incident_recording_prototype
COPY . /usr/src/incident_recording_prototype/
WORKDIR /usr/src/incident_recording_prototype
RUN chmod -R a+x /usr/src/incident_recording_prototype
RUN npm install
RUN npm rebuild node-sass
RUN rm -rf node_modules/govuk_template_jinja
RUN cp modified_node_modules/govuk_template_jinja node_modules -R
EXPOSE 3001
CMD [ "npm", "start" ]