package gg.jte.generated.ondemand;
public final class JteheaderGenerated {
	public static final String JTE_NAME = "header.jte";
	public static final int[] JTE_LINE_INFO = {3,3,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<header class=\"bg-white text-center py-16 mb-12\">\n    <h1 class=\"font-bold text-5xl mb-4\">API Server Manager</h1>\n    <p class=\"text-gray-700\">Java implemented API for server management.</p>\n</header>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
