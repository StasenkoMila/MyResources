package resources;

public class ResourceServer implements ResourceServerMBean{
    private TestResource testResource = new TestResource();

    public void setTestResource(TestResource resource) {
        this.testResource = resource;
    }

    @Override
    public String getName() {
        return testResource.getName();
    }

    @Override
    public int getAge() {
        return testResource.getAge();
    }
}
