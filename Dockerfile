FROM docker.elastic.co/logstash/logstash:8.13.0
RUN rm -f /usr/share/logstash/pipeline/logstash.conf
COPY ./logstash/pipeline/ /usr/share/logstash/pipeline/
COPY ./logstash/drivers/ /usr/share/logstash/logstash-core/lib/jars/
CMD ["logstash", "-f", "/usr/share/logstash/pipeline/logstash.conf"]