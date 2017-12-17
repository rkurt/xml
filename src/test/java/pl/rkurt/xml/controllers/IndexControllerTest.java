package pl.rkurt.xml.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import pl.rkurt.xml.parser.ParseException;
import pl.rkurt.xml.parser.Parser;
import pl.rkurt.xml.products.ProductType;
import pl.rkurt.xml.products.marvel.Marvel;
import pl.rkurt.xml.products.star_wars.StarWars;
import pl.rkurt.xml.services.XMLService;
import pl.rkurt.xml.validator.ValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    private IndexController controller;
    private ModelMap modelMap;
    private RedirectAttributes redirectAttributes;

    @Mock
    private XMLService service;

    @Mock
    private MultipartFile file;

    @Before
    public void setUp() {
        controller = new IndexController(service);
        modelMap = new ModelMap();
        redirectAttributes = new RedirectAttributesModelMap();
    }

    @Test
    public void testIndex() {
        String result = controller.index(modelMap);

        assertThat(result, is("index"));
        assertThat(((ProductType[]) modelMap.get("productTypes")).length, is(2));
        assertThat(((ProductType[]) modelMap.get("productTypes"))[0], is(ProductType.STAR_WARS));
        assertThat(((ProductType[]) modelMap.get("productTypes"))[1], is(ProductType.MARVEL));
    }

    @Test
    public void testList() {
        String result = controller.list();

        assertThat(result, is("list"));
    }

    @Test
    public void testUploadWhenEmptyFile() {
        mockEmptyFile();

        String result = controller.upload(file, ProductType.STAR_WARS, modelMap, redirectAttributes);

        assertThat(result, is("redirect:/"));
        assertThat(redirectAttributes.getFlashAttributes().get("message"), is(Parser.SELECT_FILE));
    }

    @Test
    public void testUploadWhenWrongFile() {
        mockWrongFile();

        String result = controller.upload(file, ProductType.STAR_WARS, modelMap, redirectAttributes);

        assertThat(result, is("redirect:/"));
        assertThat(redirectAttributes.getFlashAttributes().get("message"), is(Parser.SELECT_FILE));
    }

    @Test
    public void testUploadWhenIOException() throws IOException {
        mockDefaultFile();
        doThrow(new IOException()).when(file).getInputStream();

        String result = controller.upload(file, ProductType.STAR_WARS, modelMap, redirectAttributes);

        assertThat(result, is("redirect:/"));
        assertThat(redirectAttributes.getFlashAttributes().get("message"), nullValue());
    }

    @Test
    public void testUploadWhenParsingException() throws ParseException {
        mockDefaultFile();
        doThrow(new ParseException()).when(service).parseProducts(any(InputStream.class), eq(ProductType.STAR_WARS));

        String result = controller.upload(file, ProductType.STAR_WARS, modelMap, redirectAttributes);

        assertThat(result, is("redirect:/"));
        assertThat(redirectAttributes.getFlashAttributes().get("message"), is("File parsing error"));
    }

    @Test
    public void testUploadWhenValidationException() throws ValidationException {
        mockDefaultFile();
        doThrow(new ValidationException()).when(service).validateProducts(any(InputStream.class), eq(ProductType.STAR_WARS));

        String result = controller.upload(file, ProductType.STAR_WARS, modelMap, redirectAttributes);

        assertThat(result, is("redirect:/"));
        assertThat(redirectAttributes.getFlashAttributes().get("message"), is("Products validating error"));
    }

    @Test
    public void testUploadStarWars() throws ParseException {
        mockDefaultFile();
        doReturn(getStarWars()).when(service).parseProducts(any(InputStream.class), eq(ProductType.STAR_WARS));

        String result = controller.upload(file, ProductType.STAR_WARS, modelMap, redirectAttributes);

        assertThat(result, is("list"));
        assertThat((modelMap.get("productType")), is(ProductType.STAR_WARS));
        assertThat(((List) modelMap.get("products")).size(), is(2));
    }

    @Test
    public void testUploadMarvel() throws ParseException {
        mockDefaultFile();
        doReturn(getMarvel()).when(service).parseProducts(any(InputStream.class), eq(ProductType.MARVEL));

        String result = controller.upload(file, ProductType.MARVEL, modelMap, redirectAttributes);

        assertThat(result, is("list"));
        assertThat((modelMap.get("productType")), is(ProductType.MARVEL));
        assertThat(((List) modelMap.get("products")).size(), is(3));
    }

    private void mockDefaultFile() {
        doReturn(false).when(file).isEmpty();
        doReturn("text/xml").when(file).getContentType();
    }

    private void mockEmptyFile() {
        doReturn(true).when(file).isEmpty();
        doReturn("text/xml").when(file).getContentType();
    }

    private void mockWrongFile() {
        doReturn(false).when(file).isEmpty();
        doReturn("abc").when(file).getContentType();
    }

    private List<StarWars> getStarWars() {
        List<StarWars> starWars = new ArrayList<>();
        starWars.add(StarWars.builder().build());
        starWars.add(StarWars.builder().build());

        return starWars;
    }

    private List<Marvel> getMarvel() {
        List<Marvel> marvels = new ArrayList<>();
        marvels.add(Marvel.builder().build());
        marvels.add(Marvel.builder().build());
        marvels.add(Marvel.builder().build());

        return marvels;
    }
}
