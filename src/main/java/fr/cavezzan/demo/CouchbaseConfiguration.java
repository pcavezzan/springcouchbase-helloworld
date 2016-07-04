package fr.cavezzan.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.couchbase.client.java.cluster.ClusterInfo;

@Configuration
@EnableCouchbaseRepositories(basePackages={"fr.cavezzan.demo.persistence.entities.repositories"})
class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

	@Value("${couchbase.cluster.bucket}")
	private String bucketName;

	@Value("${couchbase.cluster.password}")
	private String password;

	@Value("${couchbase.cluster.ip}")
	private String ip;
	
	@Override
	protected List<String> getBootstrapHosts() {
		return Arrays.asList(this.ip);
	}
	
	@Override
	protected String getBucketName() {
		return this.bucketName;
	}

	@Override
	protected String getBucketPassword() {
		return this.password;
	}
}