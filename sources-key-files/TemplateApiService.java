package com.photoeditor.remakemefaceswapaigenerator.data.remote.service;

import com.photoeditor.remakemefaceswapaigenerator.data.remote.dto.template.TemplateCategoriesResponseDto;
import com.photoeditor.remakemefaceswapaigenerator.data.remote.dto.template.TemplateFeedResponseDto;
import com.photoeditor.remakemefaceswapaigenerator.data.remote.dto.template.TemplateListResponseDto;
import defpackage.bk0;
import defpackage.tj1;
import defpackage.w93;

/* JADX INFO: compiled from: r8-map-id-7d7e7eba03e2a3bf4e7c82ff0492649f5c831daa109059b39d92547825ffc67c */
/* JADX INFO: loaded from: classes4.dex */
public interface TemplateApiService {
    @tj1("api/categories/mobile")
    Object fetchCategories(bk0<? super TemplateCategoriesResponseDto> bk0Var);

    @tj1("api/templates/mobile")
    Object fetchTemplateFeed(bk0<? super TemplateFeedResponseDto> bk0Var);

    @tj1("api/categories/{categoryId}/templates/mobile")
    Object fetchTemplatesByCategory(@w93("categoryId") String str, bk0<? super TemplateListResponseDto> bk0Var);

    @tj1("api/templates/trending/mobile")
    Object fetchTrendingTemplates(bk0<? super TemplateListResponseDto> bk0Var);
}
