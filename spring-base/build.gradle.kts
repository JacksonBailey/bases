plugins {
    alias(libs.plugins.boot) apply false
}

allprojects {

    group = "com.example.greeter"

apply(plugin = "io.spring.dependency-management")

the<DependencyManagementExtension>().apply {
	imports {
		mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}
}
